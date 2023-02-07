output "aws_public_subnet" {
  value = aws_subnet.public.*.id
}

output "vpc_id" {
  value = aws_vpc.myvpc.id
}