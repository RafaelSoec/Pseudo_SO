package unb.modules.threads;

import java.util.concurrent.Semaphore;

public class ThreadImpl extends Thread {
	private int idThread;
	private Semaphore semaforo;
//	private Double timeExecution;
	private boolean finished;

	public ThreadImpl(int id, Semaphore semaphore) {
		this.idThread = id;
		this.semaforo = semaphore;
	}
	
	public ThreadImpl(int id, Semaphore semaphore, Double timeExecution) {
		this.idThread = id;
		this.semaforo = semaphore;
//		this.timeExecution = timeExecution;
	}
	
	 public interface Executor{
	        int execute(int a, int b);
	 }

	//Definimos inicialmente um identificador para a nossa Thread e uma referência a 
	// um semáforo que irá controlar o acesso a essas variáveis.
	//Agora vamos definir os métodos da nossa Thread, dentro da classe ProcessadorThread:
	private void toProcess() {
		try {
			System.out.println("Thread #" + idThread + " processando");
//			Thread.sleep((long) (Math.random() * 1000));
//			Thread.sleep(this.timeExecution.longValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Este método toProcess() apenas faz a thread dormir por algum tempo, simulando o efeito de um processamento longo.
	private void enterNonCriticalRegion() {
		System.out.println("Thread #" + idThread + " em região não crítica");
		toProcess();
	}

//Este método simula o acesso da Thread em uma região não crítica, ou seja, uma região ao qual não é necessário pedir uma trava. Exibimos o atual estado da Thread, para facilitar o entendimento do progama, e realizamos um processamento qualquer.
	private void enterCriticalRegion() {
		System.out.println("Thread #" + idThread + " entrando em região crítica");
		toProcess();
		System.out.println("Thread #" + idThread + " saindo da região crítica\n");
	}

	//Este outro método será utilizado para simular o acesso da Thread em uma 
	//região crítica. Ele será chamado logo após conseguir a trava do semáforo.
	public void run() {
		this.finished = false;
		enterNonCriticalRegion();
		try {
			semaforo.acquire();
			enterCriticalRegion();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.finished = true;
			semaforo.release();
		}
	}

	public boolean finished() {
		return this.finished;
	}
}