output "load_balancer_dns" {
  description = "DNS name of the load balancer"
  value       = aws_lb.franquicias_lb.dns_name
}

output "ecs_cluster_name" {
  description = "Name of the ECS cluster"
  value       = aws_ecs_cluster.franquicias_cluster.name
}

output "service_name" {
  description = "Name of the ECS service"
  value       = aws_ecs_service.franquicias_service.name
}