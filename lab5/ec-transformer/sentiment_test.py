from transformers import pipeline

# Initialize the sentiment analysis pipeline
sentiment_pipeline = pipeline("sentiment-analysis")

# Sample texts to analyze
texts = [
    "I love this course! It's amazing.",
    "This movie was terrible, I hated it.",
    "The weather is okay today, nothing special."
]

# Perform sentiment analysis
results = sentiment_pipeline(texts)

# Print results
for text, result in zip(texts, results):
    print(f"Text: {text}")
    print(f"Sentiment: {result['label']}")
    print(f"Confidence: {result['score']:.4f}")
    print()
