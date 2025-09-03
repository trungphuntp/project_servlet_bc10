package services;

import java.util.List;

import entity.Status;
import repository.StatusRepository;

public class StatusServices {
	private StatusRepository statusRepository = new StatusRepository();
	public List<Status> getAllStatus() {
		return statusRepository.findAllStatus();
	}
}
