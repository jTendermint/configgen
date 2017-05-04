# ConfigGen
Generate multiple configurations-files for Tendermint.
One YAML-File to rule them all.

## How To
* edit example.yaml
* `run StartupDeploy`
  -> a config folder with each node separated is created in the current directory
* copy the resulting config files to the tendermint config directory

### Specifying input/output directories

`run StartupDeploy arg1 arg2`
* arg1 = input yaml file
* arg2 = output directory
