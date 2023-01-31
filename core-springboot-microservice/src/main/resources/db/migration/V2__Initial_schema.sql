
CREATE SEQUENCE  IF NOT EXISTS increment_generator START WITH 1 INCREMENT BY 40;

CREATE TABLE users (
  id BIGINT NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
   deleted_at TIMESTAMP WITHOUT TIME ZONE,
   name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users ADD CONSTRAINT uc_users_name UNIQUE (name);

CREATE SEQUENCE  IF NOT EXISTS increment_generator START WITH 1 INCREMENT BY 40;

CREATE TABLE images (
  id BIGINT NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
   deleted_at TIMESTAMP WITHOUT TIME ZONE,
   title VARCHAR(255) NOT NULL,
   url TEXT NOT NULL,
   description TEXT,
   likes BIGINT,
   user_id BIGINT NOT NULL,
   CONSTRAINT pk_images PRIMARY KEY (id)
);

ALTER TABLE images ADD CONSTRAINT uc_images_url UNIQUE (url);

ALTER TABLE images ADD CONSTRAINT FK_IMAGES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

CREATE SEQUENCE  IF NOT EXISTS increment_generator START WITH 1 INCREMENT BY 40;

CREATE TABLE topics (
  id BIGINT NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
   deleted_at TIMESTAMP WITHOUT TIME ZONE,
   title VARCHAR(255) NOT NULL,
   description TEXT NOT NULL,
   CONSTRAINT pk_topics PRIMARY KEY (id)
);

ALTER TABLE topics ADD CONSTRAINT uc_topics_description UNIQUE (description);

ALTER TABLE topics ADD CONSTRAINT uc_topics_title UNIQUE (title);

CREATE TABLE image_entity_meta (
  image_entity_id BIGINT NOT NULL,
   key VARCHAR(255),
   value VARCHAR(255)
);

ALTER TABLE image_entity_meta ADD CONSTRAINT fk_imageentity_meta_on_image_entity FOREIGN KEY (image_entity_id) REFERENCES images (id);

CREATE TABLE topic_entity_meta (
  topic_entity_id BIGINT NOT NULL,
   key VARCHAR(255),
   value VARCHAR(255)
);

ALTER TABLE topic_entity_meta ADD CONSTRAINT fk_topicentity_meta_on_topic_entity FOREIGN KEY (topic_entity_id) REFERENCES topics (id);

CREATE TABLE image_topic (
  image_id BIGINT NOT NULL,
   topic_id BIGINT NOT NULL
);

ALTER TABLE image_topic ADD CONSTRAINT fk_imatop_on_image_entity FOREIGN KEY (image_id) REFERENCES images (id);

ALTER TABLE image_topic ADD CONSTRAINT fk_imatop_on_topic_entity FOREIGN KEY (topic_id) REFERENCES topics (id);