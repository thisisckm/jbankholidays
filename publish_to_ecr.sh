mvn clean package
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 456542802938.dkr.ecr.us-east-1.amazonaws.com
docker build -t jbankholidays .
docker tag jbankholidays:latest 456542802938.dkr.ecr.us-east-1.amazonaws.com/jbankholidays:latest
docker push 456542802938.dkr.ecr.us-east-1.amazonaws.com/jbankholidays:latest
