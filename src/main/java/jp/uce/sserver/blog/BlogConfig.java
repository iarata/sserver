package jp.uce.sserver.blog;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(BlogRepository repository) {
        return args -> {
            Blog blog1 = new Blog("SServer Guide", """
            <p>This server is based on <a title=\"Spring Boot\" href=\"https://spring.io/projects/spring-boot\" target=\"_blank\" rel=\"noopener\">Spring Boot</a> which allows users to create easy stand-alone, production-grade Spring based Applications.</p>
            <p>This server is using the following methods depending on which endpoint you are sending your request.</p>
            <ul>
            <li><code>GET</code></li>
            <li><code>POST</code></li>
            <li><code>DELETE</code></li>
            </ul>
            <p>All of the features that is explained below are also implemented in the front-end of the server where you can see, create and delete posts.</p>
            <h3>Main Endpoint</h3>
            <p>The main endpoint of this server is&nbsp;<code>http://localhost:8080/</code>&nbsp;or&nbsp;<code>http://localhost:8080/index.html</code>. When a client sends a&nbsp;<code>GET</code>&nbsp;request to these endpoint the response will be the html code of the main page which can be opened in the browser.</p>
            <p>But if client sends a&nbsp;<code>POST</code>&nbsp;or any other&nbsp;<em>http method</em> to this endpoint the server will return a&nbsp;<code>405 Method Not Allowed</code>&nbsp;response in json format.</p>
            <pre style=\"font-size: 12px;\">{<br />  \"timestamp\": \"2021-12-20T00:01:39.625+00:00\",<br />  \"status\": 405,<br /> \"error\": \"Method Not Allowed\",<br />  \"trace\": \"org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported....\",<br />  \"message\": \"Request method 'POST' not supported\",<br /> \"path\": \"/\"<br />}</pre>
            <h2>Blog Endpoint</h2>
            <p style=\"font-size: 12px;\"><span style=\"font-size: medium;\">The <em>blog</em> endpoint of this server is at&nbsp;<code>http://localhost:8080/blog</code>&nbsp;and this endpoint supports the <code>GET</code>, <code>POST</code> and <code>DELETE</code> methods. The example and exaplanation of each method is described below.</span></p>
            <p style=\"font-size: 12px;\">&nbsp;</p>
            <h3>GET Method</h3>
            <p>This method will return a json response will all blog posts available.</p>
            <pre>curl -XGET 'http://localhost:8080'</pre>
            <p>The response of will be an array of blog posts:</p>
            <pre>[<br />    {<br />        \"id\": \"bf94aca0-14a6-4e21-b9ac-bcaabb38224b\",<br />       \"title\": \"Code block\",<br />        \"content\": \"&lt;p&gt;This aspncfsiadnmad&amp;nbsp;&lt;/p&gt;\n&lt;p&gt;asdkas pdas mdkaspkdpsad,sapd,as&lt;/p&gt;\n&lt;pre&gt;curl &lt;a href=\\"https://localhost:8080\\"&gt;https://localhost:8080&lt;/a&gt; -method:POST &lt;/pre&gt;\"<br />       \"date\": \"2021-12-20\"<br />    }<br />]<br /><br /></pre>
            <h3>POST Method</h3>
            <p>This method will make a new blog post in the database. The body of the <code>POST</code>&nbsp;method should be defined in a json format with the following structure:</p>
            <pre>{<br />    \"title\": \"Title of the blog\",<br />    \"content\": \"Content of the blog which accepts html elements.\"<br />}</pre>
            <p>Example:</p>
            <pre>curl -XPOST -d '{
                \"title\": \"Blog 1231\",
                \"content\": \"THIS IS THE CONTENT\"
            }' 'http://localhost:8080/blog'</pre>
            <p>If the results where successfull the server will return nothing. In addition, if there is a blog with the exact title, the server will return an <code>500 Internal Server</code> error to prevent posts with exact title.</p>
            <h3>DELETE Method</h3>
            <p>This method is used to delete a blog post. The endpoint for this method is defined as:&nbsp;<code>http://localhost:8080/blog/id</code> where,&nbsp;<code>id</code>&nbsp;is refering to the blog's id which can be found in the blog endpoint.<em>&nbsp;</em></p>
            <p>Example:</p>
            <pre>curl -XDELETE 'http://localhost:8080/blog/d378be8c-8d41-4769-afac-342b2d7c1a92'</pre>
            <p>If the delete operation was successful the server will return nothing otherwise it will return an error.&nbsp;</p>
                """, LocalDate.now());

            repository.saveAll(
                List.of(blog1)
            );
        };
    }
}
