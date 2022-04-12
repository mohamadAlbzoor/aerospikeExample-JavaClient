package com.example.aerospikeexample.service;

import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;
import com.example.aerospikeexample.model.User;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    String s = "User is not found";
    AerospikeClient client = new AerospikeClient("some IP", 3000);
    WritePolicy writePolicy = new WritePolicy();


    public User findAUser(String userKey) {
        Key key = new Key("test", "demo", userKey);
        Record record = client.get(writePolicy, key);
        User user = new User(record.bins.get("name").toString(), Long.parseLong(record.bins.get("salary").toString()));
        return user;
    }

    public List<User> findAll() { // l.add(client.get(writePolicy, key).bins));
        List<User> users = new ArrayList<>();
        client.scanAll(null, "test", "demo", (key, record) -> {
            users.add(new User(record.bins.get("name").toString(), Long.parseLong(record.bins.get("salary").toString())));
        });
        return users;
    }

    public String create(User user, String userKey) {
        Key key = new Key("test", "demo", userKey);
        Bin NAME = new Bin("name", user.name);
        Bin SALARY = new Bin("salary", user.salary);
        client.put(writePolicy, key, NAME, SALARY);
        return "User has been Added";
    }

    public String delete(String userKey) {
        Key key = new Key("test", "demo",userKey);
        if(client.delete(writePolicy, key))
            s = "User has been deleted";
        else
            s="User doesnt found";
        return s;
    }

    public String update(String userKey, String Salary) {
        Key key = new Key("test", "demo", userKey);
        Bin SALARY = new Bin("salary", Salary);
        if(client.exists(writePolicy,key)){
            client.put(writePolicy, key, SALARY);
            s = "Salary has been updated";
        }
        else{
            s="User does not found";
        }
        return s;
    }
}


