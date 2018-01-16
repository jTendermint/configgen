package com.github.jtendermint.configgen.items;

import java.util.*;

import com.github.jtendermint.configgen.yaml.Node;

//# This is a TOML config file.
//# For more information, see https://github.com/toml-lang/toml
//
//proxy_app = "tcp://127.0.0.1:46658"
//moniker = "anonymous"
//node_laddr = "tcp://0.0.0.0:46656"
//seeds = ""
//fast_sync = true
//db_backend = "leveldb"
//log_level = "notice"
//rpc_laddr = "tcp://0.0.0.0:46657"

public class ConfigToml {
	public String proxy_app = "tcp://127.0.0.1:46658";
	public String moniker = "anonymous";
	public Boolean fast_sync = true;
	public String db_backend = "leveldb";
	public String log_level = "notice";

	public Map<String, String> rpc = new HashMap<String, String>();
	public transient String rpc_laddr = "tcp://0.0.0.0:46657";

	public Map<String, String> p2p = new HashMap<String, String>();
	public transient String node_laddr = "tcp://0.0.0.0:46656";
	public transient String seeds = ""; // fill later

	public Map<String, Boolean> consensus = new HashMap<String, Boolean>();
	public transient boolean create_empty_blocks = false;

	public transient Map<String, Object> other;
	public transient Node self;

	public ConfigToml() {
		rpc.put("laddr", rpc_laddr);
		p2p.put("laddr", node_laddr);
		p2p.put("seeds", seeds);
		consensus.put("create_empty_blocks", create_empty_blocks);
	}

	public void initWith(Node n) {
		proxy_app = n.proxy_app;
		moniker = n.name;
		node_laddr = n.node_laddr;

		fast_sync = n.fast_sync;
		db_backend = n.db_backend;
		log_level = n.log_level;
		rpc_laddr = n.rpc_laddr;
		other = n.other;
		self = n;

		rpc.replace("laddr", rpc_laddr);
		p2p.replace("laddr", node_laddr);
	}

	public void setSeeds(Collection<Node> seedNodes) {
		final StringBuilder toSeed = new StringBuilder();

		seedNodes.forEach(seedNode -> {
			if (!self.name.equals(seedNode.name)) {
				toSeed.append(seedNode.extip);
				toSeed.append(":");
				toSeed.append(seedNode.extport);
				toSeed.append(", ");
			}
		});

		int lastIndexComma = toSeed.lastIndexOf(", ");
		if (lastIndexComma != -1) {
			seeds = toSeed.substring(0, lastIndexComma);
		}

		p2p.replace("seeds", seeds);
	}

}
