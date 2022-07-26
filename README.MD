## This is a multi-module project for return order management.

- Each module has its own `Database`(if required), its own `configurations`, `Docker file`, `Deployable Scripts` and its own `Pom`
- Each module can be deployed independently irrespective of the other services.
- If the Service that it depends on (it's a middleware service), It'll resort to fall back response using resilience4J