package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Tachograph {
  public HashBasedTable<Long, Integer, Integer> table = HashBasedTable.create();
}
