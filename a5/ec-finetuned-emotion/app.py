from transformers import AutoTokenizer, AutoModelForSequenceClassification
import torch
from flask import Flask, jsonify, request

# Load the fine-tuned model
model_path = "c:/enterprise/tmp/model/ec-finetuned-emotion"
tokenizer = AutoTokenizer.from_pretrained(model_path)
model = AutoModelForSequenceClassification.from_pretrained(model_path)

labels = ['sadness', 'joy', 'love', 'anger', 'fear', 'surprise']

def predict_emotion(text):
    inputs = tokenizer(text, return_tensors="pt")
    outputs = model(**inputs)
    probabilities = torch.nn.functional.softmax(outputs.logits, dim=-1)
    predicted_class = torch.argmax(probabilities).item()
    confidence = probabilities[0][predicted_class].item()
    return {'class': labels[predicted_class], 'confidence': confidence}

app = Flask(__name__)

@app.route('/')
def info():
    return jsonify({'message': 'Fine-tuned emotion analysis microservice'})

@app.route('/predict', methods=['POST'])
def get_emotion():
    query = request.json
    if query['type'] == 'emotion':
        result = predict_emotion(query['text'])
        return jsonify(result)
    return jsonify({'error': 'Invalid type'}), 400

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000)