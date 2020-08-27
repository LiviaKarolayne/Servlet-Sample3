

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.print.PrinterJobWrapper;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Agendamento agendado;
    Validacao validator = new Validacao();
    
    String nome, cpf, tel, end, data;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		if (agendado == null) {
			writer.println("Não há dados há serem preenchidos. <br></br>");
			writer.println("<a href='http://localhost:8080/AtividadeServlet5/index.html'>Inserir dados</a>");
		}else{
			writer.println("Nome: " + agendado.getNome()+"<br></br>");
			writer.println("CPF: " + agendado.getCpf()+"<br></br>");
			writer.println("Data: " + agendado.getData()+"<br></br>");
			writer.println("Endereco: " + agendado.getEndereco()+"<br></br>");
			writer.println("Telefone: " + agendado.getTelefone()+"<br></br>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nome= request.getParameter("nome");
		cpf= request.getParameter("cpf");
		tel= request.getParameter("tel");
		end= request.getParameter("end");
		data= request.getParameter("data");
			
		agendado = new Agendamento();
		PrintWriter writer = response.getWriter();
		
		if (validator.vazio(nome) && validator.vazio(cpf) && validator.vazio(tel) && validator.vazio(end) && validator.vazio(data)) {
			if(validator.validar_cpf(cpf)) {
				if (validator.validar_data(data)) {
					if(validator.validar_telefone(tel)) {
						agendado.setCpf(cpf);
						agendado.setData(data);
						agendado.setEndereco(end);
						agendado.setNome(nome);
						agendado.setTelefone(Integer.parseInt(tel));
						
						writer.println("AGENDADO COM SUCESSO!");
					}else {
						writer.println("TELEFONE inválido");
					}
				}else {
					writer.println("DATA inválido");
				}
			}else {
				writer.println("CPF inválido");
			}
		}else {
			writer.println("Preencha todos os campos, por favor!");
		}
	}

}
