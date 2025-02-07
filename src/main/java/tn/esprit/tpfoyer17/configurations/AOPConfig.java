package tn.esprit.tpfoyer17.configurations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Foyer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class AOPConfig {

	@Around("execution(* tn.esprit.tpfoyer17.services.ReservationService.ajouterReservation(..))")
	public Object temps(ProceedingJoinPoint pjp) throws Throwable {
		Date start = new Date();
		Object obj = pjp.proceed();
		int time = new Date().compareTo(start);
		log.info("temps d'execution = "+time);
		return obj;
	}



		@AfterThrowing(pointcut = "execution(* tn.esprit..*.*(..))", throwing = "ex")
		public void handleExceptions(Exception ex) {
			logErrorBasedOnExceptionType(ex);
		}

		private void logErrorBasedOnExceptionType(Exception ex) {
			if (ex instanceof IOException ioEx) {
				log.error("IOException occurred: {}", ioEx.getMessage());
			} else if (ex instanceof SQLException sqlEx) {
				log.error("SQLException occurred: {}", sqlEx.getMessage());
			} else {
				log.error("Exception occurred: {}", ex.getMessage());
			}
		}


	

		

		// Pointcut pour toutes les méthodes qui commencent par "get"
		@Around("execution(* tn.esprit.tpfoyer17.services.*.get*(..))")
		public Object handleGetMethods(ProceedingJoinPoint joinPoint) throws Throwable {
			Object returnValue = joinPoint.proceed(); // Appeler la méthode originale

			if (returnValue instanceof Foyer) {
				Foyer foyer = (Foyer)  returnValue;
				if (foyer.getBlocs() != null) {
					foyer.getBlocs().size(); // Charge les cours
				}
				else if (foyer.getUniversite() != null) {
					foyer.getUniversite(); // Charge les cours
				}
			}
			else if (returnValue instanceof Bloc) {
				Bloc Bloc = (Bloc) returnValue;
				if (Bloc.getChambres() != null) {
					Bloc.getChambres().size(); // Charge les utilisateurs
				}
			}

			return returnValue; // Retourner la valeur d'origine
		}
	}


