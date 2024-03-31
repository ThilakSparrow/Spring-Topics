from flask import Flask

app = Flask(__name__)

@app.route('/')
def Index():
    return "Hello ! stepping in to python";

@app.route('/hello')
def Index1():
    return "Hello from /hello api";


if __name__ == "__main__":
    app.run(debug=True)