CREATE OR REPLACE TRIGGER student_alumni
AFTER DELETE ON student
FOR EACH ROW

BEGIN 
	INSERT INTO alumni values(:OLD.rollno,:OLD.name,:OLD.dateofadmission,:OLD.branch,:OLD.percentage,:OLD.status);
END;
/