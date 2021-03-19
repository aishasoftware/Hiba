package aisha.mapper;
import aisha.bean.Strategy;


public class StrategyMapper {
 
 public static Strategy getStrategy(Strategy dto) {
  Strategy strategy = new Strategy();
  strategy.setId(dto.getId());
  strategy.setName(dto.getName());
  strategy.setType(dto.getType());
  return strategy;
 }
  
 public static Strategy getDTO(Strategy strategy) {
  Strategy dto = new Strategy();
  dto.setId(strategy.getId());
  dto.setName(strategy.getName());
  dto.setType(strategy.getType());
  return dto;
 }
}