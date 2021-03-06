package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



/**
 * @author saung
 *
 */
@JsonPropertyOrder(alphabetic = true)
public class LinksDto {
    private List<LinkDto> links = new ArrayList<LinkDto>();

    public void addLink(LinkDto link) {
	links.add(link);
    }

    /**
     * @return the links
     */
    public List<LinkDto> getLinks() {
	return links;
    }

    /**
     * @param links
     *            the links to set
     */
    public void setLinks(List<LinkDto> links) {
	this.links = links;
    }

}
