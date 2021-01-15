CREATE DEFINER=`root`@`localhost` PROCEDURE `getTags`(IN deviceId VARCHAR(100))
BEGIN
SELECT tags.id AS id, tags.name AS name, tags.updated_at as updatedAt, tags.created_at as createdAt FROM tags
INNER JOIN(
SELECT * FROM impression where device_id = deviceId
) AS impression ON tags.impression_id = impression.id
WHERE tags.tags_status = 'ACTIVE' and tags.is_deleted = FALSE
ORDER BY CAST(tags.updated_at AS datetime) DESC;
END