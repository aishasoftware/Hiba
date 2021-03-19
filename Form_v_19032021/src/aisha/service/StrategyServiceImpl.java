package aisha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Strategy;
import aisha.dao.StrategyDAO;
import aisha.dao.StrategyDAOImpl;
 
@Service
@Transactional
public class StrategyServiceImpl implements StrategyService {
 
@Autowired
 private StrategyDAOImpl strategyDAO;
 

 
 @Override
 public Strategy getStrategy(int id) {
  return strategyDAO.getStrategy(id);
 }
 
 @Override
 public void deleteStrategy(int id) {
  strategyDAO.deleteStrategy(id);
 }
 
 @Override
 public List<Strategy> getStrategies() {
  return strategyDAO.getStrategies();
 }

@Override
public void addStrategy(aisha.bean.Strategy strategy) {
	// TODO Auto-generated method stub
	
}

@Override
public void updateStrategy(aisha.bean.Strategy strategy) {
	// TODO Auto-generated method stub
	
}
 
}