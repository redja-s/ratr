# Database Setup

Use this directory to set up a local PostgreSQL database for local execution.

Set up the database connection parameters in `application.properties`

## Default parameters

- DB Name: `postgres`
- DB Username: `user`
- DB Password: `password`

## Usage

1. `./setup.sh`

## Issues

- [Mullvad VPN] - Check if local network sharing is enabled. If not, this will disallow any attempts for e.g. localhost
