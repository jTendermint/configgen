package com.github.jtendermint.configgen.yaml;

public class DefaultValues {
    public String proxy_app;// = "tcp://127.0.0.1:46658";
    public String node_laddr;// = "tcp://0.0.0.0:46656";
    public String fast_sync;// = "true";
    public String db_backend;// = "leveldb";
    public String log_level;// = "notice";
    public String rpc_laddr;// = "tcp://0.0.0.0:46657";

    @Override
    public String toString() {
        return "DefaultValues [proxy_app=" + proxy_app + ", node_laddr=" + node_laddr + ", fast_sync=" + fast_sync + ", db_backend="
                + db_backend + ", log_level=" + log_level + ", rpc_laddr=" + rpc_laddr + "]";
    }

    public String getProxy_app() {
        return proxy_app;
    }

    public void setProxy_app(String proxy_app) {
        this.proxy_app = proxy_app;
    }

    public String getNode_laddr() {
        return node_laddr;
    }

    public void setNode_laddr(String node_laddr) {
        this.node_laddr = node_laddr;
    }

    public String getFast_sync() {
        return fast_sync;
    }

    public void setFast_sync(String fast_sync) {
        this.fast_sync = fast_sync;
    }

    public String getDb_backend() {
        return db_backend;
    }

    public void setDb_backend(String db_backend) {
        this.db_backend = db_backend;
    }

    public String getLog_level() {
        return log_level;
    }

    public void setLog_level(String log_level) {
        this.log_level = log_level;
    }

    public String getRpc_laddr() {
        return rpc_laddr;
    }

    public void setRpc_laddr(String rpc_laddr) {
        this.rpc_laddr = rpc_laddr;
    }

}
