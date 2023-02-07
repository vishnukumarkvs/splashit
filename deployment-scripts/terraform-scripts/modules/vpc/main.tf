resource "aws_vpc" "myvpc"{
    cidr_block = var.vpc_cidr
    instance_tenancy = var.instance_tenancy
    tags = {
        Name = var.tags
    }
}

resource "aws_internet_gateway" "myigw"{
    vpc_id = aws_vpc.myvpc.id

    tags = {
        Name = var.tags
    }
}

data "aws_availability_zones" "availablezones"{

}

resource "random_shuffle" "az_list" {
    input = data.aws_availability_zones.availablezones.names
    result_count = 2
}

resource "aws_subnet" "public" {
    count = var.public_subnet_count
    vpc_id = aws_vpc.myvpc.id
    cidr_block = var.public_cidrs[count.index]
    availability_zone = random_shuffle.az_list.result[count.index]
    map_public_ip_on_launch = var.map_public_ip_on_launch
    tags = {
        Name = var.tags
    }
}

resource "aws_default_route_table" "myrt"{
    default_route_table_id = aws_vpc.myvpc.default_route_table_id

    route{
        cidr_block = var.rt_route_cidr_block
        gateway_id = aws_internet_gateway.myigw.id
    }
    tags = {
        Name = var.tags
    }
}
resource "aws_route_table_association" "myrtasc" {
    count = var.puublic_subnet_count
    subnet_id = aws_subnet.public[count.index].id
    route_table_id = aws_default_route_table.myrt.id
}