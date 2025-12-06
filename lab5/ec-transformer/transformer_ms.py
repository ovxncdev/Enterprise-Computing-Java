from transformers import pipeline

sentiment_pipeline = pipeline("sentiment-analysis", model="distilbert-base-uncased-finetuned-sst-2-english")
def classify(input_text):
    return sentiment_pipeline(input_text)

from flask import Flask, jsonify, request
import json

serviceport = 8000

app = Flask(__name__)

@app.route('/')
def info():
    return jsonify({'message': 'This is a transformer based microservice.'})

@app.route('/transformer', methods=['POST'])
def get_sentiment():
    query = request.json
    if (query['type']=="sentiment") :
        response = classify(query['text']) 
        return jsonify(response)  
    
    return jsonify(response), 201

if __name__ == "__main__":
     app.run(host='0.0.0.0', port=serviceport)
