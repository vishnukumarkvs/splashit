Create a configuration file: You need to create a configuration file that contains the credentials to access your EKS cluster. You can use the following command to create a configuration file: 

aws eks update-kubeconfig --name <cluster-name>.

aws eks list-clusters

aws configure set default.region <region-name>

In a Kubernetes YAML file, you can use either a pod name, service name, or deployment name to connect to a Postgres container running inside a pod.

Using a Pod name: You can reference the pod directly by its name, but this is not recommended as pods can be rescheduled, recreated or terminated at any time.

Using a Service name: You can create a service that exposes the Postgres pod and use the service name as the hostname in your database URL. This way, you don't have to worry about the pod's name changing, as the service will automatically route traffic to the correct pod.

Using a Deployment name: Similar to a service, you can create a deployment that manages the creation and updates of your pods, and expose it using a service. You can then use the service name as the hostname in your database URL. This approach provides more stability, as the deployment ensures that your desired number of replicas are running and available at all times.

Note: The specific implementation will depend on the environment and setup of your cluster.

For connecting to a PostgreSQL database in a Spring Boot application using JDBC, you can use the following format for the database URL:

jdbc:postgresql://<host>:<port>/<database-name>

Where:

host: The hostname or IP address of the PostgreSQL server.
port: The port number of the PostgreSQL server (usually 5432).
database-name: The name of the database you want to connect to.
In a Kubernetes cluster, you can use either the Pod name or a Service name to connect to the PostgreSQL container. The Pod name is not recommended for production as it may change if the Pod is rescheduled. A better option would be to use a Service that can be used to route traffic to the desired Pod(s). The Service will provide a stable IP address and DNS name that can be used to connect to the PostgreSQL container.

In your Kubernetes YAML file, you can create a Service to expose the PostgreSQL container and define the Service name in your Spring Boot application's configuration file to use as the hostname in the database URL.

kubectl logs <pod_name> -c <container_name>
