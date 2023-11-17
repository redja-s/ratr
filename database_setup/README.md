# Database Setup

Use this directory to set up a local PostgreSQL database for local execution.

Set up the database connection parameters in `application.properties`

## Default parameters

- DB Name: `postgres`
- DB Username: `user`
- DB Password: `password`

## Usage
Run:

```shell
docker compose up --build -d
```

Ensure you use `--build` so any changes to SQL scripts are always taken into effect. Running this flag ensure the image is rebuilt
