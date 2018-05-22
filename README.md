# Examen Template

## Ansible

### Installation

- `sudo apt-get update`
- `sudo apt-get install software-properties-common`
- `sudo apt-add-repository ppa:ansible/ansible`
- `sudo apt-get update`
- `sudo apt-get install ansible`

### Configuration

Edit the `/etc/ansible/hosts` file and add the IP of the implementation VM.

```yaml
all:
  hosts:
    192.168.120.130:
      ansible_user: bram
      ansible_python_interpreter: /usr/bin/python3
```

To run the playbook provided with this template, run `sh ansible_run.sh`.