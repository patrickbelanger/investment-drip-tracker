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

package org.patrickbelanger.investment.tool.model;

import java.util.ArrayList;
import java.util.List;

import org.patrickbelanger.investment.tool.type.Account;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Patrick Belanger
 *
 */
@JsonSubTypes({
  @Type(name="accountType", value=Account.class), 
})
@Component
public class Portfolio {

  @Id
  private int id;
  
  @Getter
  @Setter
  private Account accountType;
  
  @Getter
  @Setter
  private String accountTypeOtherDescription;
 
  @Getter
  @Setter
  private List<Holding> holdings = new ArrayList<>();
  
}
