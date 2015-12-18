package workbenches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import components.Component;

public abstract class Workbench {

	private static int benchCount = 0;
	private String benchID;
	
	protected Component product;
	protected HashMap<String, Integer> storedComponents;
	protected int maxStoredComponents = 10;
	
	protected boolean assembling = false;
	protected int timeAssembling = 0;
	
	protected ArrayList<Workbench> outputTargets;
	
	public Workbench(){
		benchCount++;
		benchID = "wb" + benchCount;
		storedComponents = new HashMap<>();
		outputTargets = new ArrayList<>();
	}
	
	public void AssembleProduct(){
		boolean ready = true;
		HashMap<String, Integer> requiredComps = product.getRequiredComponents(); 
		for(String s : requiredComps.keySet()){
			if(!storedComponents.containsKey(s)){
				ready = false;
			} else {
				if(storedComponents.get(s) < requiredComps.get(s)){
					ready = false;
				}
			}
		}
		if(ready){			
			for(String s : requiredComps.keySet()){
				storedComponents.put(s, storedComponents.get(s) - requiredComps.get(s));
			}
			Assemble();
		}
	}
	
	protected void Assemble(){
		assembling = true;
		timeAssembling = 0;
		System.out.println("Assembling " + product.getName());
	}
	
	public void checkProgress(){
		if(timeAssembling < product.getAssemblyTime()){
			timeAssembling++;
			if(timeAssembling % 20 == 0){
				float prog = (float)timeAssembling / (float)product.getAssemblyTime() * 100;
				System.out.println(product.getName() + " " + prog + "% complete.");
			}
		} else {
			System.out.println(product.getName() + " assembled.");
			Random r = new Random();
			boolean sent = false;
			ArrayList<Workbench> targets = new ArrayList<>();
			targets.addAll(outputTargets);
			while(!sent && targets.size() > 0){
				int i = r.nextInt(targets.size());
				try {							
					targets.get(i).addComponent(product);
					sent = true;
					assembling = false;
				} catch (BenchFullException e) {
					targets.remove(i);
				}
			}
			if(targets.size() <= 0){
				System.out.println("All target benches full, trying again later");
			}
		}
	}
	
	public void addComponent(Component component) throws BenchFullException{
		if(getStoredComponentsCount() < maxStoredComponents){
			if(storedComponents.containsKey(component.getName())){
				storedComponents.put(component.getName(), storedComponents.get(component.getName()) + 1);
			} else {
				storedComponents.put(component.getName(), 1);
			}
		} else {
			throw new BenchFullException();
		}
		System.out.println(benchID + " receiving " + component.getName());
	}
	
	public void setProduct(Component product){
		this.product = product;
	}
	
	public void addOutputTarget(Workbench target){
		outputTargets.add(target);
	}
	
	public boolean isAssembling(){
		return assembling;
	}
	
	public int getStoredComponentsCount(){
		int count = 0;
		for(String s : storedComponents.keySet()){
			count += storedComponents.get(s);
		}
		return count;
	}
}
