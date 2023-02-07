module "vpc"{
    source = "./modules/vpc"
    tags = "eksvpc"
    instance_tenancy = "default"
    vpc_cidr = "10.0.0.0/16"
    access_ip= "0.0.0.0/0"
    public_subnet_count = 2
    public_cidrs = ["10.0.1.0/24","10.0.2.0/24"]
    map_public_ip_on_launch = true
    rt_route_cidr_block = "0.0.0.0/0"
}

module "eks" {
  source = "./modules/eks"
  aws_public_subnet = module.vpc.aws_public_subnet
  vpc_id = module.vpc.vpc_id
  cluster_name = "module-eks-${random_string.suffix.result}"
  endpoint_private_access = false
  endpoint_public_access = true
  public_access_cidrs = ["0.0.0.0/0"]
  node_group_name = "ng1"
  scaling_desired_size = 1
  scaling_max_size = 1
  scaling_min_size = 1
  instance_types = ["t3.small"]
  key_pair = aws_key_pair.generated_key.key_name
}

resource "tls_private_key" "key" {
 algorithm = "RSA"
 rsa_bits = "4096"
}
resource "aws_key_pair" "generated_key" {
 key_name = var.key_name
 public_key = tls_private_key.key.public_key_openssh
}
resource "local_file" "key_file" {
 content = tls_private_key.key.private_key_pem
 filename = "${var.key_name}.pem"
 file_permission = 0400
}