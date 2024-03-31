import threading
import requests
import time

url = "http://localhost:9003/placeOrder"
payload = {"skuCode": "SAM-S24-BLK-128G-IN", "quantity": 5}
headers = {'Content-Type': 'application/json'}


def send_request():
    try:
        response = requests.post(url, json=payload, headers=headers)
        response.raise_for_status()  # Raise an exception for non-2xx status codes
        print(f"Request successful: {response.status_code}")
    except requests.exceptions.RequestException as e:
        print(f"Error sending request: {e}")

# # Send 10,000 requests with a 1-second delay between each
# for _ in range(10000):
#     send_request()

# print("All requests sent!")

def main():
    threads = []
    for _ in range(10000):
        thread = threading.Thread(target=send_request)
        thread.start()
        threads.append(thread)

    # Wait for all threads to finish
    for thread in threads:
        thread.join()

    print("All requests sent!")

if __name__ == "__main__":
    main()
