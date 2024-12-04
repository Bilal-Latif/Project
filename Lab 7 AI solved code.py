#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd

data = {
    "Name": ["Alice", "Bob", "Charlie", "David"],
    "AI": [99, 98, None, 92],
    "CCN": [88, None, 85, 89],
    "HCI": [90, 82, None, 77]
        }

df = pd.DataFrame(data)
df = df.fillna(method='ffill')
print("DataFrame after forward fill:")
print(df)


# In[2]:


import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
import matplotlib.pyplot as plt
from sklearn import tree

data = {
    'Student_ID': [501, 502, 601, 602, 701, 702, 703],
    'Previous_Grades': ['F', 'A', 'C', 'D', 'A', 'C', 'B'],
    'Study_Hours': [9, 13, 7, 10, 9, 7, 10],
    'Attendance_Percentage': [90, 84, 79, 99, 85, 72, 97],
    'Assignment_Score': [82, 71, 50, 80, 90, 75, 88],
    'Final_Exam_Score': [90, 60, 85, 98, 88, 72, 98],
    'Performance': ['Good', 'Good', 'Average', 'Average', 'Good', 'Average', 'Excellent']
}

df = pd.DataFrame(data)
label_encoder = LabelEncoder()
df['Previous_Grades'] = label_encoder.fit_transform(df['Previous_Grades'])
df['Performance'] = label_encoder.fit_transform(df['Performance'])
X = df.drop(['Student_ID', 'Performance'], axis=1)
y = df['Performance'] 
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

clf = DecisionTreeClassifier(random_state=42)
clf.fit(X_train, y_train)
y_pred = clf.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy: {accuracy:.2f}')
print("\nClassification Report:")
print(classification_report(y_test, y_pred))
print("\nConfusion Matrix:")
print(confusion_matrix(y_test, y_pred))
plt.figure(figsize=(12, 8))
tree.plot_tree(clf, filled=True, feature_names=X.columns, class_names=label_encoder.classes_)
plt.show()


# In[3]:


import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
import matplotlib.pyplot as plt
from sklearn import tree
data = {
    'Applicant_ID': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    'Credit_Score': [650, 700, 550, 720, 480, 600, 750, 680, 590, 730],
    'Income': [45000, 60000, 30000, 80000, 25000, 35000, 90000, 70000, 40000, 75000],
    'Loan_Amount': [15000, 20000, 10000, 25000, 8000, 12000, 22000, 18000, 13000, 21000],
    'Loan_Term': [36, 48, 60, 36, 48, 60, 36, 48, 60, 36],  # in months
    'Previous_Default': ['No', 'No', 'Yes', 'No', 'Yes', 'Yes', 'No', 'No', 'Yes', 'No'],
    'Risk': ['Low', 'Low', 'High', 'Low', 'High', 'High', 'Low', 'Low', 'High', 'Low']
}
df = pd.DataFrame(data)
label_encoder = LabelEncoder()
df['Previous_Default'] = label_encoder.fit_transform(df['Previous_Default'])
df['Risk'] = label_encoder.fit_transform(df['Risk'])
X = df.drop(['Applicant_ID', 'Risk'], axis=1) 
y = df['Risk']  
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)
clf = DecisionTreeClassifier(random_state=42)
clf.fit(X_train, y_train)
y_pred = clf.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy: {accuracy:.2f}')
print("\nClassification Report:")
print(classification_report(y_test, y_pred))
print("\nConfusion Matrix:")
print(confusion_matrix(y_test, y_pred))
plt.figure(figsize=(12, 8))
tree.plot_tree(clf, filled=True, feature_names=X.columns, class_names=label_encoder.classes_)
plt.show()


# In[4]:


import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
data = {
    'Email_ID': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    'Email_Content': [
        'You have been selected for a special offer, claim your prize now!',
        'Dear customer, your bank account needs immediate verification.',
        'Great deal on electronics, 50% off, hurry up!',
        'Hey, are we still on for the meeting tomorrow?',
        'Join our online webinar on machine learning tomorrow.',
        'Limited time offer: Get your free iPhone now!',
        'Reminder: Your bill payment is due soon, pay before the deadline.',
        'Exciting news! Your favorite show is back for the season.',
        'Here’s your monthly performance review report.',
        'Discover new career opportunities with our recruitment team.'
    ],
    'Label': ['spam', 'not spam', 'spam', 'not spam', 'not spam', 'spam', 'not spam', 'not spam', 'not spam', 'not spam']
}
df = pd.DataFrame(data)
X = df['Email_Content'] 
y = df['Label']
vectorizer = CountVectorizer(stop_words='english') 
X_vec = vectorizer.fit_transform(X)  
X_train, X_test, y_train, y_test = train_test_split(X_vec, y, test_size=0.3, random_state=42)
model = MultinomialNB()
model.fit(X_train, y_train)
y_pred = model.predict(X_test)

accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy: {accuracy:.2f}')
print("\nClassification Report:")
print(classification_report(y_test, y_pred))
print("\nConfusion Matrix:")
print(confusion_matrix(y_test, y_pred))


# In[5]:


import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
data = {
    'Patient_ID': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    'Fever': ['Yes', 'No', 'Yes', 'No', 'No', 'Yes', 'Yes', 'No', 'Yes', 'No'],
    'Cough': ['Yes', 'Yes', 'Yes', 'No', 'No', 'Yes', 'No', 'No', 'Yes', 'No'],
    'Fatigue': ['Yes', 'Yes', 'Yes', 'No', 'No', 'Yes', 'Yes', 'No', 'Yes', 'No'],
    'Shortness_of_Breath': ['Yes', 'No', 'Yes', 'No', 'No', 'Yes', 'No', 'No', 'Yes', 'No'],
    'Headache': ['No', 'Yes', 'Yes', 'No', 'No', 'No', 'No', 'No', 'Yes', 'Yes'],
    'Diagnosis': ['Disease A', 'Disease B', 'Disease A', 'Disease B', 'Disease B', 
                  'Disease A', 'Disease A', 'Disease B', 'Disease A', 'Disease B']
}
df = pd.DataFrame(data)
label_encoder = LabelEncoder()
df['Fever'] = label_encoder.fit_transform(df['Fever'])
df['Cough'] = label_encoder.fit_transform(df['Cough']) 
df['Fatigue'] = label_encoder.fit_transform(df['Fatigue']) 
df['Shortness_of_Breath'] = label_encoder.fit_transform(df['Shortness_of_Breath']) 
df['Headache'] = label_encoder.fit_transform(df['Headache']) 
df['Diagnosis'] = label_encoder.fit_transform(df['Diagnosis'])  
X = df.drop(['Patient_ID', 'Diagnosis'], axis=1) 
y = df['Diagnosis'] 
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)
model = LogisticRegression(random_state=42)
model.fit(X_train, y_train)
y_pred = model.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy: {accuracy:.2f}')
print("\nClassification Report:")
print(classification_report(y_test, y_pred))
print("\nConfusion Matrix:")
print(confusion_matrix(y_test, y_pred))


# In[6]:


import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.preprocessing import LabelEncoder
data = {
    'Bedrooms': [1, 2, 3, 3, 4, 2, 5, 3, 4, 2],
    'Size_in_Sqft': [450, 800, 1200, 1500, 2000, 950, 2500, 1300, 1700, 1100],
    'Neighborhood': ['North', 'South', 'West', 'East', 'South', 'West', 'East', 'North', 'North', 'West'],
    'Year_Built': [2001, 1995, 2010, 1998, 2005, 1990, 2015, 2000, 2008, 2012],
    'Price': [150000, 250000, 350000, 400000, 500000, 275000, 600000, 375000, 475000, 300000] 
}
df = pd.DataFrame(data)
label_encoder = LabelEncoder()
df['Neighborhood'] = label_encoder.fit_transform(df['Neighborhood'])
X = df.drop(['Price'], axis=1)  
y = df['Price'] 
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)
model = LinearRegression()
model.fit(X_train, y_train)
y_pred = model.predict(X_test)
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)
print(f'Mean Squared Error (MSE): {mse:.2f}')
print(f'R-squared (R2): {r2:.2f}')
print("\nPredictions vs Actual Prices:")
predicted_vs_actual = pd.DataFrame({'Predicted': y_pred, 'Actual': y_test})
print(predicted_vs_actual)


# In[ ]:




