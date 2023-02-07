variable "vpc_cidr" {}
variable "instance_tenancy"{}
variable "access_ip" {}
variable "public_subnet_count" {}
variable "public_cidrs"{
    type = list(any)
}
variable "tags" {}

variable "map_public_ip_on_launch" {
  
}
variable "rt_route_cidr_block" {
  
}