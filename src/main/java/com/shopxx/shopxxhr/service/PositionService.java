package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> getAllPositions();

    Position saveOrUpdatePosition(Position position);

    void deletePositionById(Integer id);

    Position findOne(Position position);
}
