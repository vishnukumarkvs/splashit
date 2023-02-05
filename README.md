
# Unsplash-Clone

A photo sharing platform powered by microservices and deployed in AWS with Docker, Terraform, and Kubernetes.

![unsplash](https://user-images.githubusercontent.com/116954249/216814041-4415610f-aafa-48c3-97d1-07e962ab830a.png)


## Microservices

-   **Auth Microservice**: A Node.js, Express powered microservice that authenticates user requests and proxies them to other microservices.
-   **Core and Upload Microservices**: Spring Boot powered microservices that communicate using AWS SQS with the help of Spring Cloud.
-   **UI Microservice**: A React and Tailwind powered user interface microservice.

## Deployment

Deployed in the cloud using Docker, Terraform, and Kubernetes, this project is built to scale and handle high traffic.

## Start Sharing Today

Join the community of photographers and enthusiasts and start sharing your photos with the world!
