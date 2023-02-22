kubectl create -k .
kubectl delete -k .

two terraform apply
one after vpc module
one after eks_blueprints module

terraform destroy -target=module.eks_blueprints -auto-approve
terraform destroy -auto-approve

https://catalog.workshops.aws/eks-blueprints-terraform/en-US/010-introduction
