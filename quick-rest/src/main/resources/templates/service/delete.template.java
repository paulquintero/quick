  public void delete(Integer id){
    log.debug("Update");
    :entityType: :entityName: = this.findById(id);
    this.:repositoryName:.delete(userEntity);
  }