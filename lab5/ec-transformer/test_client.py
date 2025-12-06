import requests
import json

url = "http://127.0.0.1:8000/transformer"
data = {"type": "sentiment", "text": "I like EC"}
headers = {"Content-Type": "application/json"}

response = requests.post(url, json=data, headers=headers)
print(response.json())