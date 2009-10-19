/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package example.entity;

import java.util.HashSet;
import java.util.Set;

import org.seasar.doma.ChangedProperties;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

import example.domain.Salary;

@Entity
public class Employee {

    @Id
    @Column(name = "ID")
    Integer id;

    @Column(name = "NAME")
    String name;

    @Column(name = "SALARY")
    Salary salary;

    @Column(name = "JOB_TYPE")
    JobType jobType;

    @Version
    @Column(name = "VERSION")
    Integer version;

    @ChangedProperties
    Set<String> changedProperties = new HashSet<String>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        changedProperties.add("id");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        changedProperties.add("name");
        this.name = name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        changedProperties.add("salary");
        this.salary = salary;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        changedProperties.add("jobType");
        this.jobType = jobType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        changedProperties.add("version");
        this.version = version;
    }

    @Override
    public String toString() {
        return "Employee [changedProperties=" + changedProperties + ", id="
                + id + ", jobType=" + jobType + ", name=" + name + ", salary="
                + salary + ", version=" + version + "]";
    }

    public static enum JobType {
        SALESMAN, MANAGER, ANALYST, PRESIDENT, CLERK
    }
}
