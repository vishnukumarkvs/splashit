# Unsplash-Clone Microservices 

Welcome to the repository for Unsplash-Clone, a photo sharing platform powered by microservices and deployed in AWS using cutting-edge technologies. This platform is designed to provide a seamless user experience while ensuring scalability and reliability.


![Blank diagram](https://user-images.githubusercontent.com/116954249/218667872-7d96363a-f585-467b-846f-304cf73a6569.png)

## Tech Stack

-   Node.js
-   Express
-   Spring Boot
-   React
-   Tailwind
-   Docker
-   Terraform
-   Kubernetes
-   AWS S3
-   AWS SQS
-   Netflix Eureka

## Microservices

1.  Auth Microservice: Implemented using Node.js and Express, this microservice is responsible for authentication and proxying requests to the respective microservices.
    
2.  Core Microservice: Implemented using Spring Boot, this microservice handles all the CRUD operations for users such as fetching user data, user images, and adding images to the user.
    
3.  Upload Microservice: This microservice is responsible for uploading image files to AWS S3 buckets and is implemented using Spring Cloud.
    
4.  Comments Microservice: Implemented in Spring Boot, this microservice communicates with the Core microservice using Netflix Eureka and provides functionality for users to leave comments on photos.
    

## Deployment

Unsplash-Clone is deployed in AWS using Docker, Terraform, and Kubernetes to ensure high availability and reliability. The Core and Upload microservices are connected via AWS SQS queue, while the Comments microservice communicates with the Core microservice using Netflix Eureka.

## Contributing

Contributions are welcome to this project. If you would like to contribute, please fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License.
