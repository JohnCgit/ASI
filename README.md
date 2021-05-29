<h1>ASI</h1>
Requête:
https://attacomsian.com/blog/http-requests-resttemplate-spring-boot


Pour push:
* Window -> show view -> Other -> git staging/repositories
* Clic droit ASI Master (eclipse)
* fetch
* rebase


Dans l'onglet git staging -> commit and push


https://gist.github.com/justsml/529d0b1ddc5249095ff4b890aad5e801

https://www.w3schools.com/xml/ajax_xmlhttprequest_response.asp

------------------------------------------------------------------------------------------------------------------------------------------------

Liens utiles pour le rapport :

https://www.talend.com/fr/resources/microservices-vs-soa/?fbclid=IwAR3A-wtmFt7zdYxHnEaU_JyxL0TbM2VG99xc-Ag_ZiowfkzlkbnXO1VCxzs

https://www.pinterest.co.uk/pin/701646816919493537/

https://webdevdesigner.com/q/main-differences-between-soap-and-restful-web-services-in-java-duplicate-11986/

https://www.sepaforcorporates.com/payments-news-2/a-quick-introduction-to-apis-for-folks-interested-in-open-banking/

https://stackoverflow.com/questions/9275613/is-mvc-restful-by-design

-------------------------------------------------------------------------------------------------------------------------------------------------

public static void main(String[] args) 
{	
	 String text    =
	            "This/is/the/text/to/be/searched/" +
	            "for/occurrences/of/the http:// pattern/.";

	 

	        String patternString = "\\w+/";

	 

	        Pattern pattern = Pattern.compile(patternString);

	 
	        Matcher matcher = pattern.matcher(text);
	        List<String> list = new ArrayList<String>();
	        while (matcher.find()) {
	        	list.add(matcher.group(0));
	        }
	       
	       System.out.println(list);

	        
}
