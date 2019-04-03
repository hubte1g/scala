import com.amazonaws.services.elasticmapreduce.model.{Cluster, DescribeClusterRequest}
import com.amazonaws.services.elasticmapreduce.{AmazonElasticMapReduce, AmazonElasticMapReduceClientBuilder}
import sys.process._

object EMRMetaData {
  val emrClient:AmazonElasticMapReduce=AmazonElasticMapReduceClientBuilder.defaultClient()
  val clusterCommand= Process("cat", Seq("/mnt/var/lib/info/job-flow.json")) #| Process("jq", Seq("-r",".jobFlowId"))
  val clusterId=clusterCommand!!
  val cluster:Cluster=emrClient.describeCluster(new DescribeClusterRequest().withClusterId(
    clusterId.split('\n').map(_.trim.filter(_ >= ' ')).mkString)).getCluster
  val masterDNS:String=cluster.getMasterPublicDnsName

}
