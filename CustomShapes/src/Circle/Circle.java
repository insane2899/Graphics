package Circle;

import java.awt.Color;

import Ellipse.Ellipse;
import Graph.Graph;

public class Circle extends Ellipse{

	public Circle(Graph graph,Color boundary,Color fill,int x_centre,int y_centre,int r) {
		super();
		this.graph = graph;
		this.boundary = boundary;
		this.fill = fill;
		this.x_centre = x_centre;
		this.y_centre = y_centre;
		this.a = r;
		this.b = r;
		this.colors = new int[graph.getX_size()*2][graph.getY_size()*2];
		this.borderThickness=-1;
		this.fillThickness=-1;
	}
	public Circle(Graph grahp,Color boundary,int x_centre,int y_centre,int r) {
		super();
		this.graph = graph;
		this.boundary = boundary;
		this.fill = null;
		this.x_centre = x_centre;
		this.y_centre = y_centre;
		this.a = r;
		this.b = r;
		this.colors = new int[graph.getX_size()*2][graph.getY_size()*2];
		this.borderThickness=-1;
		this.fillThickness=-1;
	}
}
