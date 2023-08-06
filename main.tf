terraform {
  required_providers {
      google = {
        source = "hashicorp/google"
        version = "4.51.0"
        }
    }
  backend "remote" {
    organization = "leonardoscalabrini_github"
    workspaces {
      name = "desafio-dev"
    }
  }
}

provider "google" {
  credentials = var.cloud_credential
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

resource "google_cloud_run_service" "desafio-dev" {
  name     = "desafio-dev"
    location = var.region

    template {
      spec {
        containers {
          image = "leonardoscalabrini/desafio-dev:9"
        }
      }
    }

  traffic {
    percent         = 100
    latest_revision = true
  }
}

output "service_url" {
  value = google_cloud_run_service.desafio-dev.status[0].url
}