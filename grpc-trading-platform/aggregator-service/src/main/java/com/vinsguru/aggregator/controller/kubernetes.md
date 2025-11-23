what is K8S?
Kubernetes (K8s) is an open-source container orchestration platform that automates the deployment, scaling,
and management of containerized applications. It was originally developed by Google and is now maintained by the Cloud Native Computing Foundation
(CNCF). Kubernetes provides a framework to run distributed systems resiliently, handling scaling and failover for applications, providing
deployment patterns, and more. 

Kubernetes, is also known as K8s, where "K" stands for "Kube" and "8" represents the eight letters between "K" and "s".

Kubernetes is also knows as container management tool

Container : where you can run your application with all its dependencies
Management : where you can manage your containerized applications
Tool: a software that helps you to do something

WHy K8S came into existence?
With the rise of microservices architecture and containerization, managing and orchestrating containers at scale became increasingly complex. 
Organizations needed a robust solution to handle the deployment, scaling, and operation of containerized applications across clusters of machines. 
Kubernetes was created to address these challenges and provide a standardized way to manage containerized workloads.

You just write your code and push into docker image/container, rest all will be taken care by K8S

There are other container orchestration tools as well similar to K8S
- Docker compose
- Marathon
- Docker Swarm
- Apache Mesos 
but K8S is the most popular one, because it supports a lot of features like 
- deploying the container
- scheduling 
- scale up and scale down the container based on user load
- load balancing the container
- roll back and roll out the container
- monitoring the container 
- self filling and automatic bin baking etc
