CREATE TABLE project_content (
    project_id BIGINT REFERENCES project(id),
    content_id BIGINT REFERENCES content(id),
    PRIMARY KEY (project_id, content_id)
);
