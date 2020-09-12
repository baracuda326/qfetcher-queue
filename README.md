# qfetcher-queue
An example of a resource with a .dat (manifest url) file is available here:
 https://res.cloudinary.com/dvde7hpxw/raw/upload/v1599411588/test_jj00xz.dat
 
It contains 3 links to various resources:
1. https://d393vtsbp8nnlc.cloudfront.net/qsnapshot.png
2. https://d393vtsbp8nnlc.cloudfront.net/data.json
3. https://d393vtsbp8nnlc.cloudfront.net/questions.csv

For testing and documenting the end point, Swagger is used, available at the link:
http://localhost:8080/swagger-ui.html# 
or http://192.168.99.100:8080/swagger-ui.html# (for Docker container)
docker-machine ip 192.168.99.100 in my case
![](https://res.cloudinary.com/dvde7hpxw/image/upload/v1599589970/swagger1_aypqmf.png)
![](https://res.cloudinary.com/dvde7hpxw/image/upload/v1599590378/swagger2_inxsbf.png)
![](https://res.cloudinary.com/dvde7hpxw/image/upload/v1599591089/swagger3_brwdzm.png)
![](https://res.cloudinary.com/dvde7hpxw/image/upload/v1599591335/swagger4_ifampk.png)
*************************************
Example request body(json format):
{
  "filter": ["json"],
  "manifest": "https://res.cloudinary.com/dvde7hpxw/raw/upload/v1599411588/test_jj00xz.dat"
}
*************************************
Example request body(csv format):
{
  "filter": ["csv"],
  "manifest": "https://res.cloudinary.com/dvde7hpxw/raw/upload/v1599411588/test_jj00xz.dat"
}
*************************************
Example request body(image format):
{
  "filter": ["image"],
  "manifest": "https://res.cloudinary.com/dvde7hpxw/raw/upload/v1599411588/test_jj00xz.dat"
}
*************************************
Example request body(empty format):
{
  "filter": [],
  "manifest": "https://res.cloudinary.com/dvde7hpxw/raw/upload/v1599411588/test_jj00xz.dat"
}
*************************************
Example response body(empty format):
{
  "questions": [
    {
      "value": "Expert Q&A\nProblem 7: As of the beginning of this quarter, UCSD had 2016 math majors. All\nof these math majors must take an algebra class (Math 100 or Math 103), an analysis\nclass (Math 140 or Math 142) and will certainly take a combinatorics class (Math 154\nor Math 184). No student may take both of 100/103, 140/142, or 154/184.\n600 of the majors take Math 100. 800 of the majors take Math 140. 700 of the\nmajors take Math 154. 300 majors take both Math 100 and Math 140. 300 majors\ntake both Math 100 and Math 154. 400 majors take both Math 140 and Math 154.\n100 majors take all of Math 100, 140, and 154. How many majors take none of Math\n100, 140, and 154?\n2\n",
      "source": "image"
    },
    {
      "value": " Among 150 math students 42 have taken Discrete Math 32 have taken History of Math. There are 12 have taken both. (a.) How many students have take only Discrete Math; but not History of Math? (b.) How many students have taken only History of Math; but not Discrete Math?",
      "source": "csv"
    },
    {
      "value": " Which life history is exemplified by an organism that produces many offspring; but doesn't invest in the care of the offspring allowing the organism to take advantage of favorable conditions? (a) equilibrial life history (b) maturation life history (c) predictable life history (d) opportunistic life history",
      "source": "csv"
    },
    {
      "value": " Which of the following statements best distinguishes hypotheses from theories in science? a. Theories are hypotheses that have been proved. b. Hypotheses are guesses; theories are correct answers. c. Hypotheses usually are relatively narrow in scope; theories have broad explanatory power. d. Theories are proved true; hypotheses are often contradicted by experimental results.",
      "source": "csv"
    },
    {
      "value": " What is the difference between \"green chemistry\" and \"environmental chemistry\"? Nothing; they are synonyms. Environmental chemistry is a broad term; whereas green chemistry specifies industrial processes. Green chemistry is a broad term; whereas environmental chemistry specifies the atmosphere.",
      "source": "csv"
    },
    {
      "value": "To be or not to be?",
      "source": "json"
    },
    {
      "value": "What's the gist, physicist?",
      "source": "json"
    }
  ]
}
*************************************
Example response body(json format):
{
  "questions": [
    {
      "value": "To be or not to be?",
      "source": "json"
    },
    {
      "value": "What's the gist, physicist?",
      "source": "json"
    }
  ]
}
**************************************
