
from datasets import load_dataset, load_from_disk

# load dataset from Hugging Face dataset repository
emotions = load_dataset("dair-ai/emotion")
emotions

# to explore the train dataset
train_ds = emotions["train"]
train_ds

len(train_ds) 
print(train_ds.column_names)
print(train_ds.features)
print(train_ds.features['label'].names)
print(train_ds[:5])
print(train_ds["text"][:5])
labels = emotions['train'].features['label'].names
print(labels)

# Save the dataset to local file system, check the files
emotions.save_to_disk("./data/emotions")

# Load dataset from local file system
ds = load_from_disk("data/emotions")
ds

dataset_url = "https://huggingface.co/datasets/transformersbook/emotion-train-split/raw/main/train.txt"

# Download using Python's urllib
import urllib.request
urllib.request.urlretrieve(dataset_url, "train.txt")

emotions_train_local = load_dataset("csv", data_files="train.txt", sep=";", names=["text", "label"])
print(emotions_train_local)
