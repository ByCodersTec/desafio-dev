{ pkgs ? import <nixpkgs> { } }:

with pkgs;

mkShell { buildInputs = [ docker-compose nodejs ]; }
