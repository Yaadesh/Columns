from __future__ import absolute_import
from __future__ import division, print_function, unicode_literals


from sumy.parsers.html import HtmlParser
from sumy.parsers.plaintext import PlaintextParser
from sumy.nlp.tokenizers import Tokenizer
from sumy.summarizers.lsa import LsaSummarizer as Summarizer
from sumy.nlp.stemmers import Stemmer
from sumy.utils import get_stop_words


from flask import Flask, Response,request
from newspaper import Article

import json
LANGUAGE = "english"
SENTENCES_COUNT = 5



app = Flask(__name__)

@app.route("/article", methods=['POST'])
def index():
    url=request.form['url']
    #url="http://edition.cnn.com/2016/12/30/europe/russia-us-diplomats-expulsion/index.html"
    article=Article(url)
    article.download()
    article.parse()
    text=str(article.text)
    return Response(text), 200

@app.route("/summary",methods=['POST'])
def summary():
    summary=""
    url=request.form['url']
    #url="http://edition.cnn.com/2016/12/30/europe/russia-us-diplomats-expulsion/index.html"
    parser = HtmlParser.from_url(url, Tokenizer(LANGUAGE))
    stemmer = Stemmer(LANGUAGE)

    summarizer = Summarizer(stemmer)
    summarizer.stop_words = get_stop_words(LANGUAGE)

    for sentence in summarizer(parser.document, SENTENCES_COUNT):
        summary=summary+str(sentence)
    return Response(summary), 200

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080, debug=False)

