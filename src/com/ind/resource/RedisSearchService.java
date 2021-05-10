package com.ind.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ind.QueryProcessor;


@Path("/search")
public class RedisSearchService {
	
	private QueryProcessor queryProcessor=new QueryProcessor();
	
	@GET
	@Produces(value=MediaType.APPLICATION_XML)
	public Response search(@QueryParam("query") String query,@QueryParam("count") int count){

		queryProcessor.parseQuery(query);
		AllResults results=queryProcessor.processResult();
		if(results.getAllResults().size()>count){
			results.setAllResults(results.getAllResults().subList(0, count));
		}
		return Response.status(200).entity(results).build();
	}
	

}
