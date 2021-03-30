datacenter = "our_dc"
data_dir = "/etc/consul.d/consul-dir"
bind_addr = "{{GetInterfaceIP \"eth0\"}}"
node_name = "consul-server"
bootstrap_expect = 1
ui = true
client_addr = "0.0.0.0"   
server = true        


