// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.patrickbelanger.investment.tool.controller;

import java.util.List;

import org.patrickbelanger.investment.tool.model.Holding;
import org.patrickbelanger.investment.tool.service.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HoldingController class
 * 
 * @author Patrick Belanger
 *
 */
@RestController
@RequestMapping("/api/holding")
public class HoldingController {

  @Autowired
  private HoldingService holdingService;
  
  @PostMapping("/")
  public void addHolding(@RequestBody Holding newHolding) {
    holdingService.addHolding(newHolding);
  }
  
  @GetMapping("/all")
  public ResponseEntity<List<Holding>> getHoldings() {
   return holdingService.getHoldings();
  }
  
  @PutMapping("/{id}")
  public int updatePortfolio(@PathVariable("id") int id, @RequestBody Holding holding) {
    return holdingService.updateHolding(id, holding);
  }
    
  @DeleteMapping("/{id}")
  public int deletePortfolio(@PathVariable("id") int id) {
    return holdingService.deleteHolding(id);
  }
  
}
