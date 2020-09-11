package com.redd4ford.firebase_test.controller;

import com.redd4ford.firebase_test.dto.CommunityDto;
import com.redd4ford.firebase_test.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/communities")
public class CommunityController {

  private static final Logger log = LoggerFactory.getLogger(PostController.class);

  @Autowired
  private CommunityService communityService;

  @GetMapping
  public List<CommunityDto> getAll() throws InterruptedException, ExecutionException {
    return communityService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommunityDto> getOne(@PathVariable("id") Integer id)
      throws InterruptedException, ExecutionException {
    if (communityService.isExistById(id)) {
      log.info("GET    200 : id" + id);
      return new ResponseEntity<>(communityService.getById(id), HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<CommunityDto> create(@RequestBody @Valid
                                                          CommunityDto communityDto,
                                                      BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    communityDto.setId(communityService.getIdleId(communityDto));
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + communityDto.getId());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      log.info("CREATE 200 : id" + communityDto.getId());
      communityService.establishManyToMany(communityDto);
      return new ResponseEntity<>(communityService.save(communityDto,communityDto.getId()),
          HttpStatus.OK);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommunityDto> update(@PathVariable("id") Integer id,
                                                      @RequestBody @Valid
                                                          CommunityDto communityDto,
                                                      BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (communityService.isExistById(id)) {
      if (bindingResult.hasErrors()) {
        log.error("UPDATE 400 : id" + id);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("UPDATE 200 : id" + id);
      return new ResponseEntity<>(communityService.save(communityDto, communityDto.getId()),
          HttpStatus.OK);
    } else {
      log.error("UPDATE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    if (communityService.isExistById(id)) {
      communityService.delete(id);
      log.info("DELETE 200 : id" + id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
