Para executar via prompt
java -jar rest-produto-1.0.0-jar-with-dependencies.jar

para chamar o Rest pode ser usado o POSTMAN chamando a url
http://localhost:8080/labs/produto/agrupar no metodo POST o playload deve ser colocado no BODY.

Caso queira filtrar ou ordernar ambos podem ser usados
ordernar- order_by=nome_do_campo:sentido(asc ou desc)
http://localhost:8080/labs/produto/agrupar?order_by=price:desc

filtrar - filter=nome_do_campo:valor
http://localhost:8080/labs/produto/agrupar?filter=id:1
